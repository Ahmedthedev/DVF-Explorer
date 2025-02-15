import React, { useEffect, useState } from "react";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import 'react-leaflet-markercluster/styles'
import MarkerClusterGroup from "react-leaflet-markercluster";
import L from "leaflet";
import "leaflet/dist/leaflet.css";
import axios from "axios";
import 'leaflet/dist/leaflet.css';
import FilterBox from "./FilterBox";

import { Card, CardContent, Typography, Button } from "@mui/material";

const icon = new L.Icon({
    iconUrl: "https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon.png",
    iconSize: [25, 41],
    iconAnchor: [12, 41],
    popupAnchor: [1, -34],
});


const MapBiens = () => {
    const [biens, setBiens] = useState([]);
  
    const fetchBiens = async (filters = {}) => {
        const hasFilters = Object.values(filters).some((val) => val !== undefined && val !== "");
        
        // Sélectionner la bonne URL en fonction de l'état des filtres
        let urlBase = hasFilters
            ? `http://localhost:8080/api/biens/avenirFilter?`
            : `http://localhost:8080/api/biens/avenir`;
        
        // Si aucun filtre, on fait une seule requête simple sans pagination
        if (!hasFilters) {
            try {
            const response = await axios.get(urlBase);
            setBiens(response.data);
            return; // On quitte la fonction ici
            } catch (error) {
            console.error("Erreur lors de la récupération des biens", error);
            return;
            }
        }
        
        // Sinon, on boucle pour récupérer toutes les pages avec les filtres
        let allBiens = [];
        let page = 0;
        let totalPages = 1;
        
        try {
            while (page < totalPages) {
                const params = new URLSearchParams({ ...filters, page, size: 100 });
                const response = await axios.get(`${urlBase}${params.toString()}`);
            
                if (response.data && response.data.content) {
                    allBiens = [...allBiens, ...response.data.content];
                    totalPages = response.data.totalPages;
                    page++;
                } else {
                    break;
                }
            }
            setBiens(allBiens);
        } catch (error) {
            console.error("Erreur lors de la récupération des biens", error);
        }
    };
      
      
  
    useEffect(() => {
      fetchBiens();
    }, []);
  
    const handleDownload = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/export/download/csv");
        const blob = await response.blob();
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement("a");
        a.href = url;
        a.download = "export_biens.csv";
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
      } catch (error) {
        console.error("Erreur lors du téléchargement", error);
        alert("Erreur lors du téléchargement");
      }
    };
  
    return (
        <div style={{ height: "100vh", width: "100%", position: "relative" }}>
          
          <FilterBox fetchBiens={fetchBiens} />
    
          <Button 
            variant="contained" 
            color="secondary" 
            onClick={handleDownload} 
            style={{ position: "absolute", bottom: 30, left: 50, zIndex: 1000 }}
          >
            Télécharger CSV
          </Button>
            <MapContainer center={[46.603354, 1.888334]} zoom={6} style={{ height: "100%", width: "100%" }}>
                <TileLayer
                    url="https://{s}.basemaps.cartocdn.com/light_all/{z}/{x}/{y}{r}.png"
                    attribution='&copy; <a href="https://carto.com/">CARTO</a> contributors'
                />
                <MarkerClusterGroup>
                    {biens.map((bien) => (
                        <Marker key={bien.id} position={[bien.latitude, bien.longitude]} icon={icon}>
                            <Popup>
                                <Card sx={{ minWidth: 250 }}>
                                    <CardContent>
                                        <Typography variant="h6" component="div">
                                            {bien.type.charAt(0).toUpperCase() + bien.type.slice(1)} à {bien.commune}
                                        </Typography>
                                        <Typography variant="body2" color="text.secondary">
                                            Surface : {bien.surface ? `${bien.surface} m²` : "N/A"}
                                        </Typography>
                                        <Typography variant="body2" color="text.secondary">
                                            Vente prévue : {bien.saleDate}
                                        </Typography>
                                        <Typography variant="body2" color="text.secondary">
                                            Statut : {bien.status.charAt(0).toUpperCase() + bien.status.slice(1)}
                                        </Typography>
                                        <Typography variant="body2" color="text.secondary">
                                            {bien.description.replace(/\$/g, "\n").split("\n").map((line, index) => (
                                                <React.Fragment key={index}>
                                                    {line}
                                                    <br />
                                                </React.Fragment>
                                            ))}
                                        </Typography>
                                    </CardContent>
                                </Card>
                            </Popup>
                        </Marker>
                    ))}
                </MarkerClusterGroup>
            </MapContainer>
        </div>
    );
};

export default MapBiens;
