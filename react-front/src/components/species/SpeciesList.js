import React, { useEffect, useState } from 'react';
import { getSpecies, deleteSpecies, createSpecies, updateSpecies } from '../../services/api';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Button, IconButton } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import SpeciesFormModal from './SpeciesFormModal';

const SpeciesList = () => {
  const [species, setSpecies] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [editingSpeciesId, setEditingSpeciesId] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    const fetchSpecies = async () => {
      try {
        const response = await getSpecies();
        setSpecies(response.data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchSpecies();
  }, []);

  const handleDelete = async (id) => {
    try {
      await deleteSpecies(id);
      setSpecies(species.filter((species) => species.id !== id));
    } catch (err) {
      console.error('Failed to delete species:', err);
    }
  };

  const handleEdit = (id) => {
    setEditingSpeciesId(id);
    setIsModalOpen(true);
  };

  const handleAdd = () => {
    setEditingSpeciesId(null);
    setIsModalOpen(true);
  };

  const handleSave = async (speciesData) => {
    try {
      if (editingSpeciesId) {
        await updateSpecies(editingSpeciesId, speciesData);
      } else {
        const response = await createSpecies(speciesData);
        setSpecies([...species, response.data]);
      }
      setIsModalOpen(false);
      const response = await getSpecies();
      setSpecies(response.data);
    } catch (err) {
      console.error('Failed to save species:', err);
    }
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setEditingSpeciesId(null);
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div>
      <h2>Species</h2>
      <Button variant="contained" color="primary" onClick={handleAdd}>
        Add Species
      </Button>
      <TableContainer component={Paper} sx={{ mt: 2 }}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Title</TableCell>
              <TableCell>Description</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {species.map((species) => (
              <TableRow key={species.id}>
                <TableCell>{species.title}</TableCell>
                <TableCell>{species.description}</TableCell>
                <TableCell>
                  <IconButton color="primary" onClick={() => handleEdit(species.id)}>
                    <EditIcon />
                  </IconButton>
                  <IconButton color="secondary" onClick={() => handleDelete(species.id)}>
                    <DeleteIcon />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      <SpeciesFormModal
        open={isModalOpen}
        onClose={handleCloseModal}
        onSave={handleSave}
        speciesId={editingSpeciesId}
        initialData={species.find((species) => species.id === editingSpeciesId)}
      />
    </div>
  );
};

export default SpeciesList;
