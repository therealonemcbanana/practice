import React, { useState, useEffect } from 'react';
import { Modal, Box, Typography, TextField, Button, MenuItem, Select, FormControl, InputLabel, OutlinedInput, Chip } from '@mui/material';

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  boxShadow: 24,
  p: 4,
  borderRadius: 2,
};

const AnimalFormModal = ({ open, onClose, onSave, animalId, initialData, aviaries, species, food, employees }) => {
  const [name, setName] = useState(initialData?.name || '');
  const [gender, setGender] = useState(initialData?.gender || '');
  const [age, setAge] = useState(initialData?.age || '');
  const [selectedAviary, setSelectedAviary] = useState(initialData?.aviary?.id || '');
  const [selectedSpecies, setSelectedSpecies] = useState(initialData?.species?.id || '');
  const [selectedFood, setSelectedFood] = useState(initialData?.foodSet?.map(f => f.id) || []);
  const [selectedEmployees, setSelectedEmployees] = useState(initialData?.employees?.map(e => e.id) || []);

  useEffect(() => {
    if (initialData) {
      setName(initialData.name || '');
      setGender(initialData.gender || '');
      setAge(initialData.age || '');
      setSelectedAviary(initialData.aviary?.id || '');
      setSelectedSpecies(initialData.species?.id || '');
      setSelectedFood(initialData.foodSet?.map(f => f.id) || []);
      setSelectedEmployees(initialData.employees?.map(e => e.id) || []);
    }
  }, [initialData]);

  const handleSubmit = (e) => {
    e.preventDefault();
    const animalData = { 
      name, 
      gender, 
      age, 
      aviary: aviaries.find(a => a.id === selectedAviary), 
      species: species.find(s => s.id === selectedSpecies), 
      foodSet: food.filter(f => selectedFood.includes(f.id)), 
      employees: employees.filter(e => selectedEmployees.includes(e.id)) 
    };
    onSave(animalData);
  };

  return (
    <Modal open={open} onClose={onClose}>
      <Box sx={style}>
        <Typography variant="h6" component="h2" sx={{ mb: 2 }}>
          {animalId ? 'Edit Animal' : 'Add Animal'}
        </Typography>
        <form onSubmit={handleSubmit}>
          <TextField label="Name" value={name} onChange={(e) => setName(e.target.value)} fullWidth margin="normal" required />
          <FormControl fullWidth margin="normal" required>
            <InputLabel>Gender</InputLabel>
            <Select value={gender} onChange={(e) => setGender(e.target.value)}>
              <MenuItem value="male">male</MenuItem>
              <MenuItem value="female">female</MenuItem>
            </Select>
          </FormControl>
          <TextField label="Age" type="number" value={age} onChange={(e) => setAge(e.target.value)} fullWidth margin="normal" required />
          <FormControl fullWidth margin="normal" required>
            <InputLabel>Aviary</InputLabel>
            <Select value={selectedAviary} onChange={(e) => setSelectedAviary(e.target.value)}>
              {aviaries.map((aviary) => (
                <MenuItem key={aviary.id} value={aviary.id}>{aviary.id}</MenuItem>
              ))}
            </Select>
          </FormControl>
          <FormControl fullWidth margin="normal" required>
            <InputLabel>Species</InputLabel>
            <Select value={selectedSpecies} onChange={(e) => setSelectedSpecies(e.target.value)}>
              {species.map((specie) => (
                <MenuItem key={specie.id} value={specie.id}>{specie.title}</MenuItem>
              ))}
            </Select>
          </FormControl>
          <FormControl fullWidth margin="normal">
            <InputLabel>Food</InputLabel>
            <Select multiple value={selectedFood} onChange={(e) => setSelectedFood(e.target.value)} input={<OutlinedInput label="Food" />} renderValue={(selected) => (
              <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 0.5 }}>
                {selected.map((foodId) => {
                  const foodItem = food.find(f => f.id === foodId);
                  return foodItem ? <Chip key={foodItem.id} label={foodItem.name} /> : null;
                })}
              </Box>
            )}>
              {food.map((f) => (
                <MenuItem key={f.id} value={f.id}>{f.name}</MenuItem>
              ))}
            </Select>
          </FormControl>
          <FormControl fullWidth margin="normal">
            <InputLabel>Employees</InputLabel>
            <Select multiple value={selectedEmployees} onChange={(e) => setSelectedEmployees(e.target.value)} input={<OutlinedInput label="Employees" />} renderValue={(selected) => (
              <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 0.5 }}>
                {selected.map((employeeId) => {
                  const employee = employees.find(e => e.id === employeeId);
                  return employee ? <Chip key={employee.id} label={employee.name} /> : null;
                })}
              </Box>
            )}>
              {employees.map((employee) => (
                <MenuItem key={employee.id} value={employee.id}>{employee.name}</MenuItem>
              ))}
            </Select>
          </FormControl>
          <Button type="submit" variant="contained" color="primary" sx={{ mt: 2 }}>
            {animalId ? 'Save Changes' : 'Add Animal'}
          </Button>
          <Button variant="outlined" onClick={onClose} sx={{ mt: 2, ml: 2 }}>
            Cancel
          </Button>
        </form>
      </Box>
    </Modal>
  );
};

export default AnimalFormModal;
