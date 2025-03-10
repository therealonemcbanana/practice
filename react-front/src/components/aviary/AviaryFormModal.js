import React, { useState, useEffect } from 'react';
import { Modal, Box, Typography, TextField, Button } from '@mui/material';

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

const AviaryFormModal = ({ open, onClose, onSave, aviaryId, initialData }) => {
  const [size, setSize] = useState('');
  const [state, setState] = useState('');
  const [error, setError] = useState(null);

  useEffect(() => {
    if (initialData) {
      setSize(initialData.size || '');
      setState(initialData.state || '');
    } else {
      setSize('');
      setState('');
    }
  }, [initialData]);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (size <= 0) {
      setError('Size cannot be not pozitive');
      return;
    }
    if (!state.trim()) {
      setError('State cannot be empty');
      return;
    }

    const aviaryData = { size, state };
    onSave(aviaryData);
  };

  return (
    <Modal open={open} onClose={onClose}>
      <Box sx={style}>
        <Typography variant="h6" component="h2" sx={{ mb: 2 }}>
          {aviaryId ? 'Edit Aviary' : 'Add Aviary'}
        </Typography>
        {error && <Typography color="error">{error}</Typography>}
        <form onSubmit={handleSubmit}>
          <TextField
            label="Size"
            value={size}
            onChange={(e) => setSize(e.target.value)}
            fullWidth
            margin="normal"
            required
          />
          <TextField
            label="State"
            value={state}
            onChange={(e) => setState(e.target.value)}
            fullWidth
            margin="normal"
            required
          />
          <Button type="submit" variant="contained" color="primary" sx={{ mt: 2 }}>
            {aviaryId ? 'Save Changes' : 'Add Aviary'}
          </Button>
          <Button variant="outlined" onClick={onClose} sx={{ mt: 2, ml: 2 }}>
            Cancel
          </Button>
        </form>
      </Box>
    </Modal>
  );
};

export default AviaryFormModal;
