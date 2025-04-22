import React from "react";

const ItemTable = ({ items }) => {
  return (
    <table className="table-auto w-full border-collapse border border-gray-300">
      <thead>
        <tr>
          <th className="border border-gray-300 px-4 py-2">Item Name</th>
          <th className="border border-gray-300 px-4 py-2">Quantity</th>
          <th className="border border-gray-300 px-4 py-2">Price</th>
        </tr>
      </thead>
      <tbody>
        {items.map((item, index) => (
          <tr key={index}>
            <td className="border border-gray-300 px-4 py-2">{item.name}</td>
            <td className="border border-gray-300 px-4 py-2">
              {item.quantity}
            </td>
            <td className="border border-gray-300 px-4 py-2">${item.price}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default ItemTable;
