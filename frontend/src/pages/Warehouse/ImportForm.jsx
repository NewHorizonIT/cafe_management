import React, { useState } from "react";

const ImportForm = () => {
  const [importData, setImportData] = useState({
    material: "",
    quantity: "",
    unitPrice: "",
    totalPrice: "",
    supplier: "",
    responsiblePerson: "",
    importDate: "",
  });

  const handleSubmit = (e) => {
    e.preventDefault();

    // Calculate total price (Quantity * Unit Price)
    const total =
      parseFloat(importData.quantity) * parseFloat(importData.unitPrice);
    setImportData({ ...importData, totalPrice: total });

    console.log("Import Data Submitted:", importData);
    setImportData({
      material: "",
      quantity: "",
      unitPrice: "",
      totalPrice: "",
      supplier: "",
      responsiblePerson: "",
      importDate: "",
    });
  };

  return (
    <div className="p-5">
      <h2 className="text-xl font-bold mb-4">Tạo hóa đon nhập nguyên liệu</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
        {/* material  Name */}
        <input
          type="text"
          placeholder="Tên nguyên liệu"
          value={importData.material}
          onChange={(e) =>
            setImportData({ ...importData, material: e.target.value })
          }
          className="border px-3 py-2 w-full"
        />

        {/* Quantity */}
        <input
          type="number"
          placeholder="Số lượng"
          value={importData.quantity}
          onChange={(e) =>
            setImportData({ ...importData, quantity: e.target.value })
          }
          className="border px-3 py-2 w-full"
        />

        {/* Unit Price */}
        <input
          type="number"
          placeholder="Dơn giá"
          value={importData.unitPrice}
          onChange={(e) =>
            setImportData({ ...importData, unitPrice: e.target.value })
          }
          className="border px-3 py-2 w-full"
        />

        {/* Supplier Name */}
        <input
          type="text"
          placeholder="Nhà cung cấp"
          value={importData.supplier}
          onChange={(e) =>
            setImportData({ ...importData, supplier: e.target.value })
          }
          className="border px-3 py-2 w-full"
        />

        {/* Responsible Person */}
        <input
          type="text"
          placeholder="Người phụ trách"
          value={importData.responsiblePerson}
          onChange={(e) =>
            setImportData({ ...importData, responsiblePerson: e.target.value })
          }
          className="border px-3 py-2 w-full"
        />

        {/* Import Date */}
        <input
          type="date"
          value={importData.importDate}
          onChange={(e) =>
            setImportData({ ...importData, importDate: e.target.value })
          }
          className="border px-3 py-2 w-full"
        />

        {/* Submit Button */}
        <button
          type="submit"
          className="bg-blue-500 text-white px-4 py-2 rounded"
        >
          Submit
        </button>
      </form>

      {/* Display the Total Price */}
      {importData.totalPrice && (
        <div className="mt-4">
          <strong>Total Price:</strong> {importData.totalPrice.toLocaleString()}
        </div>
      )}
    </div>
  );
};

export default ImportForm;
