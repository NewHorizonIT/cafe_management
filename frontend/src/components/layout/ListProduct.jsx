import React from "react";
import { CardProduct } from "../ui";

const ListProduct = ({ products = [] }) => {
  return (
    <div className="grid grid-cols-1 lg:grid-cols-3 gap-5">
      {products.length > 0 ? (
        products.map((product) => <CardProduct {...product} />)
      ) : (
        <h1>Load error</h1>
      )}
    </div>
  );
};

export default ListProduct;
