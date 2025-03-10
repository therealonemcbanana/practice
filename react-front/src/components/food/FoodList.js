import React, { useEffect, useState } from 'react';
import { getFood, deleteFood, createFood, updateFood } from '../../services/api';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Button, IconButton } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import FoodFormModal from './FoodFormModal';

const FoodList = () => {
  const [foods, setFoods] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [editingFoodId, setEditingFoodId] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    const fetchFoods = async () => {
      try {
        const response = await getFood();
        setFoods(response.data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchFoods();
  }, []);

  const handleDelete = async (id) => {
    try {
      await deleteFood(id);
      setFoods(foods.filter((food) => food.id !== id));
    } catch (err) {
      console.error('Failed to delete food:', err);
    }
  };

  const handleEdit = (id) => {
    setEditingFoodId(id);
    setIsModalOpen(true);
  };

  const handleAdd = () => {
    setEditingFoodId(null);
    setIsModalOpen(true);
  };

  const handleSave = async (foodData) => {
    try {
      if (editingFoodId) {
        await updateFood(editingFoodId, foodData);
      } else {
        const response = await createFood(foodData);
        setFoods([...foods, response.data]);
      }
      setIsModalOpen(false);
      const response = await getFood();
      setFoods(response.data);
    } catch (err) {
      console.error('Failed to save food:', err);
    }
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setEditingFoodId(null);
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div>
      <h2>Food</h2>
      <Button variant="contained" color="primary" onClick={handleAdd}>
        Add Food
      </Button>
      <TableContainer component={Paper} sx={{ mt: 2 }}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Name</TableCell>
              <TableCell>Amount</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {foods.map((food) => (
              <TableRow key={food.id}>
                <TableCell>{food.name}</TableCell>
                <TableCell>{food.amount}</TableCell>
                <TableCell>
                  <IconButton color="primary" onClick={() => handleEdit(food.id)}>
                    <EditIcon />
                  </IconButton>
                  <IconButton color="secondary" onClick={() => handleDelete(food.id)}>
                    <DeleteIcon />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      <FoodFormModal
        open={isModalOpen}
        onClose={handleCloseModal}
        onSave={handleSave}
        foodId={editingFoodId}
        initialData={foods.find((food) => food.id === editingFoodId)}
      />
    </div>
  );
};

export default FoodList;
