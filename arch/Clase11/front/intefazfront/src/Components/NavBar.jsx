import { AppBar, Toolbar, Button } from '@mui/material';
import { Link } from 'react-router-dom';

const NavBar = () => (
  <AppBar position="fixed" style={{ width: '100%' }}>
    <Toolbar>
      <Button color="inherit" component={Link} to="/">Home</Button>
      <Button color="inherit" component={Link} to="/about">About</Button>
    </Toolbar>
  </AppBar>
);

export default NavBar;