import React, { useState, useEffect, use } from "react";
import Container from "@/components/layout/Container";
import CardProduct from "@/components/ui/CardProduct";
import AdvancedSearch from "@/components/ui/AdvancedSearch";
import { useParams } from "react-router-dom";
import useDrinkStore from "@/store/useDrinkStore";
import { ListProduct } from "@/components/layout";

const DrinksByCategory = () => {
  const { category } = useParams();

  // const [drinks, setDrinks] = useState([]);

  const drinks = useDrinkStore((state) => state.drinks);

  // useEffect(() => {
  //   const fetchDrinks = async () => {
  //     try {
  //       const response = await fetch(
  //         `https://api.example.com/drinks?category=${category}`
  //       );
  //       if (!response.ok) {
  //         throw new Error("Network response was not ok");
  //       }
  //       const data = await response.json();
  //       setDrinks(data);
  //     } catch (error) {
  //       console.error("Error fetching drinks:", error);
  //     }
  //   };

  //   fetchDrinks();
  // });

  return (
    <Container className="py-12">
      <h1 className="text-2xl font-bold mb-4">
        Danh sách đồ uống loại {category}
      </h1>
      <AdvancedSearch />
      <ListProduct
        products={drinks.filter((drink) => drink.category === category)}
      />
    </Container>
  );
};

export default DrinksByCategory;
