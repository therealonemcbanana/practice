import axios from 'axios';
import config from './config';

const api = axios.create({
  baseURL: config.apiUrl,
});

export const getAnimals = () => api.get('/animal');
export const getAnimalById = (id) => api.get(`/animal/${id}`);
export const createAnimal = (animal) => api.post('/animal', animal);
export const updateAnimal = (id, animal) => api.put(`/animal/${id}`, animal);
export const deleteAnimal = (id) => api.delete(`/animal/${id}`);

export const getAviaries = () => api.get('/aviary');
export const getAviaryById = (id) => api.get(`/aviary/${id}`);
export const createAviary = (aviary) => api.post('/aviary', aviary);
export const updateAviary = (id, aviary) => api.put(`/aviary/${id}`, aviary);
export const deleteAviary = (id) => api.delete(`/aviary/${id}`);

export const getFood = () => api.get('/food');
export const getFoodById = (id) => api.get(`/food/${id}`);
export const createFood = (food) => api.post('/food', food);
export const updateFood = (id, food) => api.put(`/food/${id}`, food);
export const deleteFood = (id) => api.delete(`/food/${id}`);

export const getEmployees = () => api.get('/employee');
export const getEmployeeById = (id) => api.get(`/employee/${id}`);
export const createEmployee = (employee) => api.post('/employee', employee);
export const updateEmployee = (id, employee) => api.put(`/employee/${id}`, employee);
export const deleteEmployee = (id) => api.delete(`/employee/${id}`);

export const getSpecies = () => api.get('/species');
export const getSpeciesById = (id) => api.get(`/species/${id}`);
export const createSpecies = (species) => api.post('/species', species);
export const updateSpecies = (id, species) => api.put(`/species/${id}`, species);
export const deleteSpecies = (id) => api.delete(`/species/${id}`);

export default api;