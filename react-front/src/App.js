import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import { AppBar, Toolbar, Typography, Button, Container } from '@mui/material';
import AnimalList from './components/animal/AnimalList';
import AviaryList from './components/aviary/AviaryList';
import SpeciesList from './components/species/SpeciesList';
import FoodList from './components/food/FoodList';
import EmployeeList from './components/employee/EmployeeList';

const App = () => {
  return (
    <Router>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Zoo Management
          </Typography>
          <Button color="inherit" component={Link} to="/animals">
            Animals
          </Button>
          <Button color="inherit" component={Link} to="/aviaries">
            Aviaries
          </Button>
          <Button color="inherit" component={Link} to="/species">
            Species
          </Button>
          <Button color="inherit" component={Link} to="/food">
            Food
          </Button>
          {<Button color="inherit" component={Link} to="/employees">
            Employees
          </Button>}
        </Toolbar>
      </AppBar>
      <Container>
        <Routes>
          <Route path="/animals" element={<AnimalList />} />
          <Route path="/aviaries" element={<AviaryList />} />
          <Route path="/species" element={<SpeciesList />} />
          <Route path="/food" element={<FoodList />} />
          <Route path="/employees" element={<EmployeeList />} />
          <Route path="/" element={<AnimalList />} />
        </Routes>
      </Container>
    </Router>
  );
};

export default App;
