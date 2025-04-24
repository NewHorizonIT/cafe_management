import { Container, HeroSection, ListProduct } from "@/components/layout";
import Heading from "@/components/ui/Heading";
import React, { use } from "react";
import useDrinkStore from "@/store/useDrinkStore";

const Home = () => {
  const products = useDrinkStore((state) => state.drinks);

  return (
    <>
      <HeroSection heroImg="https://dreamheatersystem.com/wp-content/uploads/2019/04/hero-section-image.jpg" />
      <Container className="py-20">
        <Heading
          title="Danh sách đồ uống phổ biến"
          className="text-center"
          des="Chúng tôi cung cấp nhiều loại đồ uống khác nhau, từ cà phê đến trà và nước trái cây."
        />
        <ListProduct products={products} />
      </Container>
    </>
  );
};

export default Home;
