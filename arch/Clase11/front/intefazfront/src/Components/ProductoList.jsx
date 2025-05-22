// src/components/ProductoList.js
//npm install @mui/material @emotion/react @emotion/styled
import React, { useEffect, useState } from 'react';
import {
  Table, TableBody, TableCell, TableContainer,
  TableHead, TableRow, Paper, Button, CircularProgress, TextField, Dialog, DialogActions, DialogContent, DialogTitle
} from '@mui/material';
import ProductoService from '../Services/ServiceProduct';

const ProductoList = () => {
  const [productos, setProductos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [currentProduct, setCurrentProduct] = useState(null);
  const [isAddDialogOpen, setIsAddDialogOpen] = useState(false);
  const [newProduct, setNewProduct] = useState({ nombre: '', precio: '', stock: '' });

  useEffect(() => {
    ProductoService.getProductos()
      .then(res => {
        setProductos(res.data);
        setLoading(false);
      })
      .catch(err => {
        setError('Hubo un problema al obtener los productos');
        setLoading(false);
      });
  }, []);

  // Función para eliminar producto
  const handleDelete = (id) => {
    ProductoService.deleteProducto(id)
      .then(() => {
        setProductos(productos.filter(producto => producto.id !== id));
      })
      .catch(err => console.error("Error eliminando producto", err));
  };

  // Función para abrir el modal de edición
  const handleEditOpen = (product) => {
    setCurrentProduct(product);
    setIsDialogOpen(true);
  };

  // Función para cerrar el modal de edición
  const handleEditClose = () => {
    setIsDialogOpen(false);
    setCurrentProduct(null);
  };

  // Función para manejar cambios en el formulario de edición
  const handleEditChange = (e) => {
    const { name, value } = e.target;
    setCurrentProduct({ ...currentProduct, [name]: value });
  };

  // Función para guardar los cambios de edición
  const handleEditSubmit = () => {
    ProductoService.updateProducto(currentProduct.id, currentProduct)
      .then(() => {
        setProductos(productos.map(product => product.id === currentProduct.id ? currentProduct : product));
        handleEditClose();
      })
      .catch(err => console.error("Error actualizando producto", err));
  };

  // Funciones para agregar producto
  const handleAddOpen = () => setIsAddDialogOpen(true);
  const handleAddClose = () => {
    setIsAddDialogOpen(false);
    setNewProduct({ nombre: '', precio: '', stock: '' });
  };
  const handleAddChange = (e) => {
    const { name, value } = e.target;
    setNewProduct({ ...newProduct, [name]: value });
  };
  const handleAddSubmit = () => {
    ProductoService.addProducto(newProduct)
      .then(res => {
        setProductos([...productos, { ...newProduct, ...res.data }]);
        handleAddClose();
        console.log("Producto agregado");
      })
      .catch(err => console.error("Error agregando producto", err));
  };

  if (loading) {
    return <CircularProgress />;
  }

  return (
    <div>
      {error && <p>{error}</p>}
      <div
        style={{
          position: 'fixed',
          top: 72, 
          right: 32,
          zIndex: 1300
        }}
      >
        <Button variant="contained" color="success" onClick={handleAddOpen}>
          Agregar producto
        </Button>
      </div>
      <TableContainer
        component={Paper}
        style={{
          marginTop: 90,
          width: '100%',
          maxWidth: '1200px',
          minHeight: '600px'
        }}
      >
        <Table
          sx={{
            minWidth: 1200,
            fontSize: '1.3rem'
          }}
        >
          <TableHead>
            <TableRow>
              <TableCell sx={{ fontSize: '1.2rem', padding: '20px' }}>Nombre</TableCell>
              <TableCell sx={{ fontSize: '1.2rem', padding: '20px' }}>Precio</TableCell>
              <TableCell sx={{ fontSize: '1.2rem', padding: '20px' }}>Stock</TableCell>
              <TableCell sx={{ fontSize: '1.2rem', padding: '20px' }}>Acciones</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {productos.map((p) => (
              <TableRow key={p.id} sx={{ fontSize: '1.1rem', height: '70px' }}>
                <TableCell sx={{ fontSize: '1.1rem', padding: '20px' }}>{p.nombre}</TableCell>
                <TableCell sx={{ fontSize: '1.1rem', padding: '20px' }}>{p.precio}</TableCell>
                <TableCell sx={{ fontSize: '1.1rem', padding: '20px' }}>{p.stock}</TableCell>
                <TableCell sx={{ fontSize: '1.1rem', padding: '20px' }}>
                  <Button
                    onClick={() => handleEditOpen(p)}
                    style={{ marginRight: '10px', fontSize: '1rem', padding: '10px 20px' }}
                  >
                    Editar
                  </Button>
                  <Button
                    onClick={() => handleDelete(p.id)}
                    style={{ fontSize: '1rem', padding: '10px 20px' }}
                  >
                    Eliminar
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      {/* Modal Agregar Producto */}
      <Dialog open={isAddDialogOpen} onClose={handleAddClose}>
        <DialogTitle>Agregar Producto</DialogTitle>
        <DialogContent>
          <TextField
            label="Nombre"
            name="nombre"
            value={newProduct.nombre}
            onChange={handleAddChange}
            fullWidth
            margin="normal"
          />
          <TextField
            label="Precio"
            name="precio"
            type="number"
            value={newProduct.precio}
            onChange={handleAddChange}
            fullWidth
            margin="normal"
          />
          <TextField
            label="Stock"
            name="stock"
            type="number"
            value={newProduct.stock}
            onChange={handleAddChange}
            fullWidth
            margin="normal"
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleAddClose} color="primary">Cancelar</Button>
          <Button onClick={handleAddSubmit} color="primary">Agregar</Button>
        </DialogActions>
      </Dialog>

      {/* Modal de Edición */}
      {currentProduct && (
        <Dialog open={isDialogOpen} onClose={handleEditClose}>
          <DialogTitle>Editar Producto</DialogTitle>
          <DialogContent>
            <TextField
              label="Nombre"
              name="nombre"
              value={currentProduct.nombre}
              onChange={handleEditChange}
              fullWidth
              margin="normal"
            />
            <TextField
              label="Precio"
              name="precio"
              type="number"
              value={currentProduct.precio}
              onChange={handleEditChange}
              fullWidth
              margin="normal"
            />
            <TextField
              label="Stock"
              name="stock"
              type="number"
              value={currentProduct.stock}
              onChange={handleEditChange}
              fullWidth
              margin="normal"
            />
          </DialogContent>
          <DialogActions>
            <Button onClick={handleEditClose} color="primary">
              Cancelar
            </Button>
            <Button onClick={handleEditSubmit} color="primary">
              Guardar
            </Button>
          </DialogActions>
        </Dialog>
      )}
    </div>
  );
};

export default ProductoList;
