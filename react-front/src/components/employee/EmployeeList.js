import React, { useEffect, useState } from 'react';
import { getEmployees, deleteEmployee, createEmployee, updateEmployee } from '../../services/api';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Button, IconButton } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import EmployeeFormModal from './EmployeeFormModal';

const EmployeeList = () => {
  const [employees, setEmployees] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [editingEmployeeId, setEditingEmployeeId] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    const fetchEmployees = async () => {
      try {
        const response = await getEmployees();
        setEmployees(response.data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchEmployees();
  }, []);

  const handleDelete = async (id) => {
    try {
      await deleteEmployee(id);
      setEmployees(employees.filter((employee) => employee.id !== id));
    } catch (err) {
      console.error('Failed to delete employee:', err);
    }
  };

  const handleEdit = (id) => {
    setEditingEmployeeId(id);
    setIsModalOpen(true);
  };

  const handleAdd = () => {
    setEditingEmployeeId(null);
    setIsModalOpen(true);
  };

  const handleSave = async (employeeData) => {
    try {
      if (editingEmployeeId) {
        await updateEmployee(editingEmployeeId, employeeData);
      } else {
        const response = await createEmployee(employeeData);
        setEmployees([...employees, response.data]);
      }
      setIsModalOpen(false);
      const response = await getEmployees();
      setEmployees(response.data);
    } catch (err) {
      console.error('Failed to save employee:', err);
    }
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setEditingEmployeeId(null);
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div>
      <h2>Employees</h2>
      <Button variant="contained" color="primary" onClick={handleAdd}>
        Add Employee
      </Button>
      <TableContainer component={Paper} sx={{ mt: 2 }}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Name</TableCell>
              <TableCell>Salary</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {employees.map((employee) => (
              <TableRow key={employee.id}>
                <TableCell>{employee.name}</TableCell>
                <TableCell>{employee.salary}</TableCell>
                <TableCell>
                  <IconButton color="primary" onClick={() => handleEdit(employee.id)}>
                    <EditIcon />
                  </IconButton>
                  <IconButton color="secondary" onClick={() => handleDelete(employee.id)}>
                    <DeleteIcon />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      <EmployeeFormModal
        open={isModalOpen}
        onClose={handleCloseModal}
        onSave={handleSave}
        employeeId={editingEmployeeId}
        initialData={employees.find((employee) => employee.id === editingEmployeeId)}
      />
    </div>
  );
};

export default EmployeeList;
