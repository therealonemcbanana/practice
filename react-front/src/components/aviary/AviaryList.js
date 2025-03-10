import React, { useEffect, useState } from 'react';
import { getAviaries, deleteAviary, createAviary, updateAviary } from '../../services/api';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Button, IconButton } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import AviaryFormModal from './AviaryFormModal';

const AviaryList = () => {
  const [aviaries, setAviaries] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [editingAviaryId, setEditingAviaryId] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    const fetchAviaries = async () => {
      try {
        const response = await getAviaries();
        setAviaries(response.data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchAviaries();
  }, []);

  const handleDelete = async (id) => {
    try {
      await deleteAviary(id);
      setAviaries(aviaries.filter((aviary) => aviary.id !== id));
    } catch (err) {
      console.error('Failed to delete aviary:', err);
    }
  };

  const handleEdit = (id) => {
    setEditingAviaryId(id);
    setIsModalOpen(true);
  };

  const handleAdd = () => {
    setEditingAviaryId(null);
    setIsModalOpen(true);
  };

  const handleSave = async (aviaryData) => {
    try {
      if (editingAviaryId) {
        await updateAviary(editingAviaryId, aviaryData);
      } else {
        const response = await createAviary(aviaryData);
        setAviaries([...aviaries, response.data]);
      }
      setIsModalOpen(false);
      const response = await getAviaries();
      setAviaries(response.data);
    } catch (err) {
      console.error('Failed to save aviary:', err);
    }
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setEditingAviaryId(null);
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div>
      <h2>Aviaries</h2>
      <Button variant="contained" color="primary" onClick={handleAdd}>
        Add Aviary
      </Button>
      <TableContainer component={Paper} sx={{ mt: 2 }}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Size</TableCell>
              <TableCell>State</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {aviaries.map((aviary) => (
              <TableRow key={aviary.id}>
                <TableCell>{aviary.size}</TableCell>
                <TableCell>{aviary.state}</TableCell>
                <TableCell>
                  <IconButton color="primary" onClick={() => handleEdit(aviary.id)}>
                    <EditIcon />
                  </IconButton>
                  <IconButton color="secondary" onClick={() => handleDelete(aviary.id)}>
                    <DeleteIcon />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      <AviaryFormModal
        open={isModalOpen}
        onClose={handleCloseModal}
        onSave={handleSave}
        aviaryId={editingAviaryId}
        initialData={aviaries.find((aviary) => aviary.id === editingAviaryId)}
      />
    </div>
  );
};

export default AviaryList;
