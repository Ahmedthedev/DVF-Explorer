import React from "react";
import MapBiens from "./components/MapBien";
import { ThemeProvider, createTheme } from "@mui/material/styles";
import { Button, Typography } from "@mui/material"; // Import de test

const theme = createTheme({
  palette: { mode: 'light' },
});

function App() {
  return (
    <ThemeProvider theme={theme}>
      <div style={{ height: "100vh", width: "100%" }}>
        <MapBiens />
      </div>
    </ThemeProvider>
  );
}


export default App;
