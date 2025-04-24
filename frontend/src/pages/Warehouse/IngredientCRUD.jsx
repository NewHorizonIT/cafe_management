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
      <h2 className="text-xl font-bold mb-4">Quản lý nguyên liệu </h2>
      <div className="mb-4">
        <input
          type="text"
          placeholder="Nguyên liệu"
          value={newIngredient.name}
          onChange={(e) =>
            setNewIngredient({ ...newIngredient, name: e.target.value })
          }
          className="border px-3 py-2 mr-2"
        />
        <input
          type="number"
          placeholder="Số lượng"
          value={newIngredient.quantity}
          onChange={(e) =>
            setNewIngredient({ ...newIngredient, quantity: e.target.value })
          }
          className="border px-3 py-2 mr-2"
        />
        <input
          type="text"
          placeholder="Đơn vị"
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
          Thêm nguyên liệu
        </button>
      </div>
      <table className="w-full border-collapse border border-gray-300">
        <thead>
          <tr className="bg-gray-100">
            <th className="border px-4 py-2">ID</th>
            <th className="border px-4 py-2">Tên</th>
            <th className="border px-4 py-2">Số lượng</th>
            <th className="border px-4 py-2">Đơn vị</th>
            <th className="border px-4 py-2">Hành động</th>
          </tr>
        </thead>
        <tbody>
          {ingredients.map((ingredient) => (
            <tr key={ingredient.id}>
              <td className="border px-4 py-2">{ingredient.id}</td>
              <td className="border px-4 py-2">{ingredient.name}</td>
              <td className="border px-4 py-2">{ingredient.quantity}</td>
              <td className="border px-4 py-2">{ingredient.unit}</td>
              <td className="border px-4 py-2 flex gap-2">
                <button
                  onClick={() => handleDeleteIngredient(ingredient.id)}
                  className="btn bg-primary text-primary-content px-3 py-1 rounded"
                >
                  Xóa
                </button>
                <button
                  onClick={() => handleDeleteIngredient(ingredient.id)}
                  className="btn bg-primary text-primary-content px-3 py-1 rounded"
                >
                  Chi tiết
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
