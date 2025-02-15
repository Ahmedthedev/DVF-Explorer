import React, { useState } from "react";
import { Box, Slider, Typography, Button, TextField } from "@mui/material";

const FilterBox = ({ fetchBiens }) => {
  const [prix, setPrix] = useState([50000, 500000]);
  const [date, setDate] = useState("");

  // Appliquer les filtres
  const handleFilterApply = () => {
    fetchBiens({
      minPrix: prix[0],
      maxPrix: prix[1],
      minSaleDate: date ? new Date(date).toISOString() : null,
    });
  };

  // Réinitialiser les filtres et la map
  const handleReset = () => {
    setPrix([50000, 500000]);
    setDate("");
    fetchBiens({});
  };

  return (
    <Box sx={{ position: "absolute", top: 30, left: 70, zIndex: 1000, p: 2, bgcolor: "white", borderRadius: 2, boxShadow: 3, width: 300 }}>
      <Typography variant="h6">Filtres</Typography>

      {/* Slider Prix */}
      <Typography variant="body2">Prix ({prix[0]}€ - {prix[1]}€)</Typography>
      <Slider
        value={prix}
        onChange={(e, newValue) => setPrix(newValue)}
        valueLabelDisplay="auto"
        min={10000}
        max={1000000}
      />

      {/* Champ Date */}
      <TextField
        type="date"
        label="Date min de vente"
        fullWidth
        variant="outlined"
        size="small"
        margin="normal"
        InputLabelProps={{ shrink: true }}
        onChange={(e) => setDate(e.target.value)}
      />

      {/* Boutons Appliquer & Reset */}
      <Button variant="contained" color="primary" fullWidth onClick={handleFilterApply} sx={{ mt: 2 }}>
        Appliquer Filtres
      </Button>
      <Button variant="outlined" color="secondary" fullWidth onClick={handleReset} sx={{ mt: 1 }}>
        Reset Filtres
      </Button>
    </Box>
  );
};

export default FilterBox;
