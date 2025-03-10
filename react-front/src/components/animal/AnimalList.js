import React, { useEffect, useState } from 'react';
import { getAnimals, deleteAnimal, updateAnimal, createAnimal, getAviaries, getSpecies, getFood, getEmployees } from '../../services/api';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Button, IconButton } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import AnimalFormModal from './AnimalFormModal';

const AnimalList = () => {
  const [animals, setAnimals] = useState([]);
  const [aviaries, setAviaries] = useState([]);
  const [species, setSpecies] = useState([]);
  const [food, setFood] = useState([]);
  const [employees, setEmployees] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [editingAnimalId, setEditingAnimalId] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [animalRes, aviaryRes, speciesRes, foodRes, employeeRes] = await Promise.all([
          getAnimals(), getAviaries(), getSpecies(), getFood(), getEmployees()
        ]);
        setAnimals(animalRes.data);
        setAviaries(aviaryRes.data);
        setSpecies(speciesRes.data);
        setFood(foodRes.data);
        setEmployees(employeeRes.data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, []);

  const handleDelete = async (id) => {
    try {
      await deleteAnimal(id);
      setAnimals(animals.filter((animal) => animal.id !== id));
    } catch (err) {
      console.error('Failed to delete animal:', err);
    }
  };

  const handleEdit = (id) => {
    setEditingAnimalId(id);
    setIsModalOpen(true);
  };

  const handleAdd = () => {
    setEditingAnimalId(null);
    setIsModalOpen(true);
  };

  const handleSave = async (animalData) => {
    try {
      if (editingAnimalId) {
        await updateAnimal(editingAnimalId, animalData);
      } else {
        const response = await createAnimal(animalData);
        setAnimals([...animals, response.data]);
      }
      setIsModalOpen(false);
      const response = await getAnimals();
      setAnimals(response.data);
    } catch (err) {
      console.error('Failed to save animal:', err);
    }
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setEditingAnimalId(null);
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div>
      <h2>Animals</h2>
      <Button variant="contained" color="primary" onClick={handleAdd}>
        Add Animal
      </Button>
      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Name</TableCell>
              <TableCell>Gender</TableCell>
              <TableCell>Age</TableCell>
              <TableCell>Aviary</TableCell>
              <TableCell>Species</TableCell>
              <TableCell>Food</TableCell>
              <TableCell>Employees</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {animals.map((animal) => (
              <TableRow key={animal.id}>
                <TableCell>{animal.id}</TableCell>
                <TableCell>{animal.name}</TableCell>
                <TableCell>{animal.species?.title}</TableCell>
                <TableCell>{animal.gender}</TableCell>
                <TableCell>{animal.age}</TableCell>
                <TableCell>{animal.aviary?.id}</TableCell>
                <TableCell>{animal.foodSet.map(f => <div key={f.id}>{f.name}</div>)}</TableCell>
                <TableCell>{animal.employees.map(e => <div key={e.id}>{e.name}</div>)}</TableCell>
                <TableCell>
                  <IconButton color="primary" onClick={() => handleEdit(animal.id)}>
                    <EditIcon />
                  </IconButton>
                  <IconButton color="secondary" onClick={() => handleDelete(animal.id)}>
                    <DeleteIcon />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <AnimalFormModal
        open={isModalOpen}
        onClose={handleCloseModal}
        onSave={handleSave}
        animalId={editingAnimalId}
        initialData={animals.find((animal) => animal.id === editingAnimalId)}
        aviaries={aviaries}
        species={species}
        food={food}
        employees={employees}
      />
    </div>
  );
};

export default AnimalList;
