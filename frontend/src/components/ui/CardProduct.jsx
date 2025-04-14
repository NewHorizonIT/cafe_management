import isNewProduct from "@/utils/checkNew";
import React from "react";
import { NavLink } from "react-router-dom";
import { Button } from ".";

const CardProduct = ({ thumnail, name, createdAt, price, id }) => {
  return (
    <div className="card  shadow-lg border-2 border-neutral-content bg-base-300">
      <figure>
        <img src={thumnail} alt="product" />
      </figure>
      <div className="card-body">
        <h2 className="card-title">
          {name}
          {isNewProduct(createdAt) ? (
            <div className="badge badge-error">NEW</div>
          ) : (
            <></>
          )}
        </h2>
        <p className="text-primary text-xl">{price}</p>
        <div className="card-actions justify-start">
          <Button
            lable={
              <NavLink className="text-primary-content" to={`products/${id}`}>
                Chi tiet
              </NavLink>
            }
            className="bg-primary"
          />
          <Button
            lable="Dat hang"
            className="text-primary-content bg-primary"
          />
        </div>
      </div>
    </div>
  );
};

export default CardProduct;
