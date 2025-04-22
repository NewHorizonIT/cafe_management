import React, { useState } from "react";
import { Button, Modal, Table } from "@/components/ui";
import { FormAddProduct } from "@/components/forms";
import { SearchIcon } from "@/components/icons";

const ProductCRUD = () => {
  const [products, setProducts] = useState([
    {
      id: 1,
      name: "Espresso",
      supplier: "CoffeeHouse",
      price: 3.5,
      stock: 100,
    },
    { id: 2, name: "Latte", supplier: "StarBeans", price: 4.0, stock: 50 },
  ]);

  const [isOpenFormAddProduct, setIsOpenFormAddProduct] = useState(false);
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [newProduct, setNewProduct] = useState({
    name: "",
    supplier: "",
    price: "",
    stock: "",
  });
  const [searchTerm, setSearchTerm] = useState("");
  const [sortBy, setSortBy] = useState(null);
  const [sortOrder, setSortOrder] = useState("asc");
  const [filteredProducts, setFilteredProducts] = useState([]);

  const handleAddProduct = (product) => {
    const newId = Math.max(...products.map((p) => p.id)) + 1;
    setProducts([...products, { id: newId, ...product }]);
  };

  const handleDeleteProduct = (id) => {
    setProducts(products.filter((product) => product.id !== id));
  };

  const handleEditProduct = (product) => {
    setSelectedProduct(product);
    setNewProduct({
      name: product.name,
      supplier: product.supplier,
      price: product.price,
      stock: product.stock,
    });
  };

  const handleSort = (column) => {
    if (sortBy === column) {
      setSortOrder(sortOrder === "asc" ? "desc" : "asc");
    } else {
      setSortBy(column);
      setSortOrder("asc");
    }
  };

  const handleFilter = () => {
    const filter = products
      .filter((p) =>
        [p.name, p.supplier].some((val) =>
          val.toLowerCase().includes(searchTerm.toLowerCase())
        )
      )
      .sort((a, b) => {
        if (!sortBy) return 0;
        const valA = a[sortBy];
        const valB = b[sortBy];
        if (typeof valA === "string") {
          return sortOrder === "asc"
            ? valA.localeCompare(valB)
            : valB.localeCompare(valA);
        } else {
          return sortOrder === "asc" ? valA - valB : valB - valA;
        }
      });

    setFilteredProducts(filter);
  };

  return (
    <div>
      <h2 className="text-xl font-bold mb-4">Manage Products</h2>
      <div className="flex">
        <input
          type="text"
          placeholder="Search by name or supplier..."
          className="border px-3 py-2 mb-4 w-full"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
        <Button
          className="btn bg-primary text-primary-content"
          eventHandler={handleFilter}
        >
          <SearchIcon />
        </Button>
      </div>

      <Button
        className="btn bg-primary text-primary-content my-3"
        eventHandler={() => setIsOpenFormAddProduct(true)}
      >
        Thêm sản phẩm
      </Button>

      {isOpenFormAddProduct && (
        <Modal isOpen onClose={() => setIsOpenFormAddProduct(false)}>
          <FormAddProduct
            onAdd={handleAddProduct}
            onClose={() => setIsOpenFormAddProduct(false)}
          />
        </Modal>
      )}

      <Table
        columns={[
          { header: "ID", accessor: "id" },
          {
            header: "Name",
            accessor: "name",
            render: (row) => (
              <button onClick={() => handleSort("name")} className="font-bold">
                {row.name}
              </button>
            ),
          },
          {
            header: "Supplier",
            accessor: "supplier",
            render: (row) => (
              <button
                onClick={() => handleSort("supplier")}
                className="font-bold"
              >
                {row.supplier}
              </button>
            ),
          },
          {
            header: "Price",
            accessor: "price",
            render: (row) => (
              <button onClick={() => handleSort("price")}>
                ${row.price.toFixed(2)}
              </button>
            ),
          },
          {
            header: "Stock",
            accessor: "stock",
            render: (row) => (
              <button onClick={() => handleSort("stock")}>{row.stock}</button>
            ),
          },
          {
            header: "Actions",
            accessor: "actions",
            render: (row) => (
              <div className="flex gap-2">
                <button
                  onClick={() => handleEditProduct(row)}
                  className="bg-yellow-400 text-white px-2 py-1 rounded"
                >
                  Edit
                </button>
                <button
                  onClick={() => handleDeleteProduct(row.id)}
                  className="bg-red-500 text-white px-2 py-1 rounded"
                >
                  Delete
                </button>
              </div>
            ),
          },
        ]}
        data={filteredProducts}
      />

      {/* Modal chỉnh sửa sản phẩm */}
      {selectedProduct && (
        <Modal
          isOpen
          onClose={() => setSelectedProduct(null)}
          title="Edit Product"
        >
          <div className="grid grid-cols-1 gap-2 mb-4">
            <input
              type="text"
              placeholder="Product Name"
              value={newProduct.name}
              onChange={(e) =>
                setNewProduct({ ...newProduct, name: e.target.value })
              }
              className="border px-3 py-2"
            />
            <input
              type="text"
              placeholder="Supplier"
              value={newProduct.supplier}
              onChange={(e) =>
                setNewProduct({ ...newProduct, supplier: e.target.value })
              }
              className="border px-3 py-2"
            />
            <input
              type="number"
              placeholder="Price"
              value={newProduct.price}
              onChange={(e) =>
                setNewProduct({ ...newProduct, price: e.target.value })
              }
              className="border px-3 py-2"
            />
            <input
              type="number"
              placeholder="Stock"
              value={newProduct.stock}
              onChange={(e) =>
                setNewProduct({ ...newProduct, stock: e.target.value })
              }
              className="border px-3 py-2"
            />
            <button
              onClick={() => {
                setProducts((prev) =>
                  prev.map((p) =>
                    p.id === selectedProduct.id
                      ? {
                          ...selectedProduct,
                          ...newProduct,
                          price: parseFloat(newProduct.price),
                          stock: parseInt(newProduct.stock),
                        }
                      : p
                  )
                );
                setSelectedProduct(null);
              }}
              className="bg-blue-500 text-white px-4 py-2 rounded"
            >
              Save Changes
            </button>
          </div>
        </Modal>
      )}
    </div>
  );
};

export default ProductCRUD;
