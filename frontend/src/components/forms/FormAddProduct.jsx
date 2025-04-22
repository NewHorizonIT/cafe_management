import React, { useState } from "react";

// Component nhập liệu cho nguyên liệu
const MaterialSelect = ({ materials, onChange }) => (
  <div className="sm:col-span-2">
    <label className="block text-sm font-medium mb-1">Nguyên liệu</label>
    <select
      onChange={(e) =>
        onChange([...e.target.selectedOptions].map((option) => option.value))
      }
      className="select select-bordered w-full"
    >
      {materials.map((mat) => (
        <option key={mat.id} value={mat.id}>
          {mat.name}
        </option>
      ))}
    </select>
  </div>
);

const FormAddProduct = ({ onAdd }) => {
  const [newProduct, setNewProduct] = useState({
    name: "",
    price: "",
    stock: "",
    description: "",
    category_id: "",
    thumbnail: null,
    components: [],
  });

  const [categories, setCategories] = useState([
    { id: "1", name: "Soft Drinks" },
    { id: "2", name: "Juices" },
    { id: "3", name: "Coffee" },
    { id: "4", name: "Tea" },
  ]);

  const [materials, setMaterials] = useState([
    { id: "1", name: "Sugar" },
    { id: "2", name: "Milk" },
    { id: "3", name: "Water" },
    { id: "4", name: "Lemon" },
    { id: "5", name: "Ice" },
  ]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setNewProduct((prev) => ({ ...prev, [name]: value }));
  };

  const handleThumbnail = (e) => {
    setNewProduct((prev) => ({ ...prev, thumbnail: e.target.files[0] }));
  };

  const handleMaterialChange = (selected) => {
    setNewProduct((prev) => {
      return { ...prev, components: [...prev.components, ...selected] };
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const formData = new FormData();
    formData.append("name", newProduct.name);
    formData.append("price", parseFloat(newProduct.price));
    formData.append("stock", parseInt(newProduct.stock));
    formData.append("description", newProduct.description);
    formData.append("category_id", newProduct.category_id);
    if (newProduct.thumbnail) {
      formData.append("thumbnail", newProduct.thumbnail);
    }
    formData.append("components", JSON.stringify(newProduct.components));

    console.log(newProduct);
    onAdd();
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="bg-base-100 p-4 rounded shadow border border-base-content/10 mb-6"
    >
      <h3 className="text-lg font-semibold mb-4">Add New Drink</h3>

      <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
        <div>
          <label className="block text-sm font-medium mb-1">Product Name</label>
          <input
            type="text"
            name="name"
            value={newProduct.name}
            onChange={handleChange}
            className="input input-bordered w-full"
            required
          />
        </div>

        <div>
          <label className="block text-sm font-medium mb-1">Price ($)</label>
          <input
            type="number"
            name="price"
            step="0.01"
            value={newProduct.price}
            onChange={handleChange}
            className="input input-bordered w-full"
            required
          />
        </div>

        <div>
          <label className="block text-sm font-medium mb-1">Stock</label>
          <input
            type="number"
            name="stock"
            value={newProduct.stock}
            onChange={handleChange}
            className="input input-bordered w-full"
            required
          />
        </div>

        <div>
          <label className="block text-sm font-medium mb-1">Thumbnail</label>
          <input
            type="file"
            accept="image/*"
            onChange={handleThumbnail}
            className="file-input file-input-bordered w-full"
          />
        </div>

        <div>
          <label className="block text-sm font-medium mb-1">Category</label>
          <select
            name="category_id"
            value={newProduct.category_id}
            onChange={handleChange}
            className="select select-bordered w-full"
            required
          >
            <option value="">-- Select category --</option>
            {categories.map((cat) => (
              <option key={cat.id} value={cat.id}>
                {cat.name}
              </option>
            ))}
          </select>
        </div>

        <div className="sm:col-span-2">
          <label className="block text-sm font-medium mb-1">Description</label>
          <textarea
            name="description"
            value={newProduct.description}
            onChange={handleChange}
            className="textarea textarea-bordered w-full"
          ></textarea>
        </div>

        <MaterialSelect materials={materials} onChange={handleMaterialChange} />
      </div>

      <div className="mt-4 text-right">
        <button type="submit" className="btn btn-primary">
          Add Drink
        </button>
      </div>
    </form>
  );
};

export default FormAddProduct;
