import { useState, useEffect } from "react";

function WordList() {
  const [words, setWords] = useState([]);
  const [input, setInput] = useState("");
  const [warning, setWarning] = useState("");
  const [visible, setVisible] = useState(true);

  useEffect(() => {
    if (visible) {
      setWords([]); // Reinicia la lista cuando el componente se vuelve a mostrar
    }
  }, [visible]); // Se ejecuta cada vez que cambia "visible"

  const addWord = () => {
    if (input.trim() === "") return;
    if (words.includes(input)) {
      setWarning("La palabra ya existe en la lista.");
      return;
    }
    setWords([...words, input]);
    setInput("");
    setWarning("");
  };

  return (
    <div className="flex flex-col items-center  min-h-screen">
      <button
        onClick={() => setVisible(!visible)}
        className="mb-4 bg-gray-700 text-white px-4 py-2 rounded hover:bg-gray-800"
      >
        {visible ? "Ocultar Lista" : "Mostrar Lista"}
      </button>
      {visible && (
        <div className="p-4  rounded-xl shadow-md ">
          <h2 className="text-xl font-bold mb-2">Lista de Palabras</h2>
          <ul className="mb-2">
            {words.map((word, index) => (
              <li key={index} className="text-gray-700">{word}</li>
            ))}
          </ul>
          <input
            type="text"
            value={input}
            onChange={(e) => setInput(e.target.value)}
            className="shadow-lg p-1 rounded w-full mb-2"
            placeholder="Añadir palabra"
          />
          <button
            onClick={addWord}
            className="bg-blue-500 text-white p-1 rounded w-full hover:bg-blue-600"
          >
            Agregar
          </button>
          {warning && <p className="text-red-500 mt-2">{warning}</p>}
        </div>
      )}
    </div>
  );
}

export default WordList;
