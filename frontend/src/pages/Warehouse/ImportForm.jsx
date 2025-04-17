import React, { useState } from "react";

const ImportForm = () => {
  const [importData, setImportData] = useState({
    product: "",
    quantity: "",
    supplier: "",
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Import Data Submitted:", importData);
    setImportData({ product: "", quantity: "", supplier: "" });
  };

  return (
    <div className="p-5">
      <h2 className="text-xl font-bold mb-4">Create Import Form</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
        <input
          type="text"
          placeholder="Product Name"
          value={importData.product}
          onChange={(e) =>
            setImportData({ ...importData, product: e.target.value })
          }
          className="border px-3 py-2 w-full"
        />
        <input
          type="number"
          placeholder="Quantity"
          value={importData.quantity}
          onChange={(e) =>
            setImportData({ ...importData, quantity: e.target.value })
          }
          className="border px-3 py-2 w-full"
        />
        <input
          type="text"
          placeholder="Supplier Name"
          value={importData.supplier}
          onChange={(e) =>
            setImportData({ ...importData, supplier: e.target.value })
          }
          className="border px-3 py-2 w-full"
        />
        <button
          type="submit"
          className="bg-blue-500 text-white px-4 py-2 rounded"
        >
          Submit
        </button>
      </form>
    </div>
  );
};

export default ImportForm;
