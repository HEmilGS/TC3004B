import { useState, useEffect } from "react";

function AlbumList() {
  const [albums, setAlbums] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch("https://jsonplaceholder.typicode.com/albums")
      .then((response) => response.json())
      .then((data) => {
        setAlbums(data.slice(0, 5)); 
        setLoading(false);
      })
      .catch((err) => {
        setError("Error al cargar los datos");
        setLoading(false);
      });
  }, []);

  if (loading) return <p className="text-center">Cargando...</p>;
  if (error) return <p className="text-red-500 text-center">{error}</p>;

  return (
    <div className="p-4 rounded-xl shadow-xl">
      <h2 className="text-xl font-bold mb-2">Lista de Álbumes</h2>
      <ul className="space-y-2">
        {albums.map((album) => (
          <li key={album.id} className="p-2 border rounded-lg bg-[#cbc5ad]">

            <p className="text-gray-700 font-semibold">Usuario: {album.id}</p>
            <p className="text-gray-600">ID: {album.userId}</p>
            <p className="text-gray-600">Título: {album.title}</p>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default AlbumList;
