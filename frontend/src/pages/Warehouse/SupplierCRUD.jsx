import React, { useState } from "react";

const SupplierCRUD = () => {
  const [suppliers, setSuppliers] = useState([
    { id: 1, name: "Coffee Beans Co.", contact: "123-456-7890" },
    { id: 2, name: "Milk Suppliers Ltd.", contact: "987-654-3210" },
  ]);
  const [newSupplier, setNewSupplier] = useState({ name: "", contact: "" });

  const handleAddSupplier = () => {
    setSuppliers([...suppliers, { id: Date.now(), ...newSupplier }]);
    setNewSupplier({ name: "", contact: "" });
  };

  const handleDeleteSupplier = (id) => {
    setSuppliers(suppliers.filter((supplier) => supplier.id !== id));
  };

  return (
    <div className="p-5">
      <h2 className="text-xl font-bold mb-4">Quản lý nhà cung cấp</h2>
      <div className="mb-4">
        <input
          type="text"
          placeholder="Tên nhà cung cấp"
          value={newSupplier.name}
          onChange={(e) =>
            setNewSupplier({ ...newSupplier, name: e.target.value })
          }
          className="border px-3 py-2 mr-2"
        />
        <input
          type="text"
          placeholder="Liên hệ"
          value={newSupplier.contact}
          onChange={(e) =>
            setNewSupplier({ ...newSupplier, contact: e.target.value })
          }
          className="border px-3 py-2 mr-2"
        />
        <button
          onClick={handleAddSupplier}
          className="bg-blue-500 text-white px-4 py-2 rounded"
        >
          Thêm nhà cung cấp
        </button>
      </div>
      <table className="w-full border-collapse border border-gray-300">
        <thead>
          <tr className="bg-gray-100">
            <th className="border px-4 py-2">ID</th>
            <th className="border px-4 py-2">Name</th>
            <th className="border px-4 py-2">Contact</th>
            <th className="border px-4 py-2">Actions</th>
          </tr>
        </thead>
        <tbody>
          {suppliers.map((supplier) => (
            <tr key={supplier.id}>
              <td className="border px-4 py-2">{supplier.id}</td>
              <td className="border px-4 py-2">{supplier.name}</td>
              <td className="border px-4 py-2">{supplier.contact}</td>
              <td className="border px-4 py-2 flex gap-2">
                <button
                  onClick={() => handleDeleteSupplier(supplier.id)}
                  className="btn bg-primary text-primary-content px-3 py-1 rounded"
                >
                  Xoá
                </button>
                <button
                  onClick={() => handleDeleteSupplier(supplier.id)}
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

export default SupplierCRUD;
