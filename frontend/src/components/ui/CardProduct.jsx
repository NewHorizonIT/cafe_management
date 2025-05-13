import isNewProduct from "@/utils/checkNew";
import React from "react";
import { NavLink } from "react-router-dom";
import useCartStore from "@/store/useCartStore";

const CardProduct = ({ thumbnail, name, createdAt, price, id }) => {
  const addToCart = useCartStore((state) => state.addToCart);

  return (
    <div className="card shadow-lg border-2 border-neutral-content bg-base-300">
      <figure>
        <img
          src={thumbnail ? thumbnail : "https://placehold.co/300x200"}
          alt="product"
          className="w-full h-48 object-cover"
        />
      </figure>
      <div className="card-body">
        <h2 className="card-title text-lg font-bold">
          {name}
          {isNewProduct(createdAt) && (
            <div className="badge badge-error ml-2">NEW</div>
          )}
        </h2>
        <p className="text-primary text-xl font-semibold">{price}</p>
        <div className="card-actions flex justify-between mt-4">
          <NavLink
            to={`/products/${id}`}
            className="btn btn-outline btn-primary"
          >
            Xem chi tiết
          </NavLink>
          <button
            onClick={() => addToCart({ id, name, price, thumbnail })}
            className="btn btn-primary text-primary-content"
          >
            Thêm vào giỏ hàng
          </button>
        </div>
      </div>
    </div>
  );
};

export default CardProduct;
