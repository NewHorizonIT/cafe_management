import React from "react";
import { CardProduct } from "../ui";

const ListProduct = ({ products = [] }) => {
  return (
    <div className="grid grid-cols-1 lg:grid-cols-3 gap-5">
      {products.length > 0 ? (
        products.map((product, i) => <CardProduct key={i} {...product} />)
      ) : (
        <div className="col-span-3 text-center">
          <p className="text-xl font-semibold">Không có sản phẩm nào</p>
        </div>
      )}
    </div>
  );
};

export default ListProduct;
