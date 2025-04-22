import React from "react";
import { Container } from "@/components/layout";
import Heading from "@/components/ui/Heading";
import Button from "@/components/ui/Button";
import milkCoffee from "../assets/milk-coffe.jpg";
import { useNavigate } from "react-router-dom";
import { ArrowLeftIcon, CloseIcon } from "@/components/icons";

const ProductDetail = () => {
  const navigate = useNavigate();

  const product = {
    id: 1,
    thumnail: milkCoffee,
    name: "Cà phê sữa đá",
    price: "25,000 VND",
    createdAt: "2025-04-01",
    category: "coffee",
    ingredients: ["Cà phê", "Sữa đặc", "Đá"],
    rating: 4.5,
    description:
      "Cà phê sữa đá là một loại đồ uống phổ biến ở Việt Nam, được làm từ cà phê nguyên chất và sữa đặc, thường được phục vụ với đá.",
  };

  return (
    <Container className="py-20 relative">
      <div className="flex flex-col md:flex-row items-center md:items-start bg-base-100 shadow-lg p-6 rounded-lg relative">
        <div className="md:w-1/2 flex justify-center">
          <img
            src={product.thumnail}
            alt={product.name}
            className="w-3/4 h-auto rounded-lg shadow-md"
          />
        </div>
        <div className="md:w-1/2 mt-6 md:mt-0 md:pl-10">
          <Heading
            title={product.name}
            className="text-left"
            des={product.description}
          />
          <p className="text-lg font-semibold mt-2">Giá: {product.price}</p>
          <div className="mt-4">
            <h3 className="text-md font-bold">Nguyên liệu:</h3>
            <ul className="list-none flex gap-2 flex-wrap">
              {product.ingredients.map((ingredient, index) => (
                <li key={index}>{ingredient},</li>
              ))}
            </ul>
          </div>
          <div className="mt-4 flex gap-3">
            <h3 className="text-md font-bold">Danh mục:</h3>
            <p>{product.category}</p>
          </div>
          <p className="text-md font-semibold mt-2">
            Đánh giá: {product.rating} ⭐
          </p>
          <Button
            className="mt-6 bg-primary text-primary-content"
            onClick={() => console.log("Add to cart")}
          >
            Thêm vào giỏ hàng
          </Button>
        </div>
        <Button
          className="absolute top-4 left-4 flex items-center p-2  cursor-pointer bg-transparent shadow-none border-0"
          eventHandler={() => navigate(-1)}
        >
          <ArrowLeftIcon />
        </Button>
      </div>
    </Container>
  );
};

export default ProductDetail;
