import React, { useState } from "react";

const IngredientCRUD = () => {
  const [ingredients, setIngredients] = useState([
    { id: 1, name: "Milk", quantity: 20, unit: "liters" },
    { id: 2, name: "Coffee Beans", quantity: 10, unit: "kg" },
  ]);
  const [newIngredient, setNewIngredient] = useState({
    name: "",
    quantity: "",
    unit: "",
  });

  const handleAddIngredient = () => {
    setIngredients([
      ...ingredients,
      {
        id: Date.now(),
        ...newIngredient,
        quantity: parseFloat(newIngredient.quantity),
      },
    ]);
    setNewIngredient({ name: "", quantity: "", unit: "" });
  };

  const handleDeleteIngredient = (id) => {
    setIngredients(ingredients.filter((ingredient) => ingredient.id !== id));
  };

  return (
    <div className="p-5">
      <h2 className="text-xl font-bold mb-4">Manage Ingredients</h2>
      <div className="mb-4">
        <input
          type="text"
          placeholder="Ingredient Name"
          value={newIngredient.name}
          onChange={(e) =>
            setNewIngredient({ ...newIngredient, name: e.target.value })
          }
          className="border px-3 py-2 mr-2"
        />
        <input
          type="number"
          placeholder="Quantity"
          value={newIngredient.quantity}
          onChange={(e) =>
            setNewIngredient({ ...newIngredient, quantity: e.target.value })
          }
          className="border px-3 py-2 mr-2"
        />
        <input
          type="text"
          placeholder="Unit"
          value={newIngredient.unit}
          onChange={(e) =>
            setNewIngredient({ ...newIngredient, unit: e.target.value })
          }
          className="border px-3 py-2 mr-2"
        />
        <button
          onClick={handleAddIngredient}
          className="bg-blue-500 text-white px-4 py-2 rounded"
        >
          Add Ingredient
        </button>
      </div>
      <table className="w-full border-collapse border border-gray-300">
        <thead>
          <tr className="bg-gray-100">
            <th className="border px-4 py-2">ID</th>
            <th className="border px-4 py-2">Name</th>
            <th className="border px-4 py-2">Quantity</th>
            <th className="border px-4 py-2">Unit</th>
            <th className="border px-4 py-2">Actions</th>
          </tr>
        </thead>
        <tbody>
          {ingredients.map((ingredient) => (
            <tr key={ingredient.id}>
              <td className="border px-4 py-2">{ingredient.id}</td>
              <td className="border px-4 py-2">{ingredient.name}</td>
              <td className="border px-4 py-2">{ingredient.quantity}</td>
              <td className="border px-4 py-2">{ingredient.unit}</td>
              <td className="border px-4 py-2">
                <button
                  onClick={() => handleDeleteIngredient(ingredient.id)}
                  className="bg-red-500 text-white px-3 py-1 rounded"
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default IngredientCRUD;
