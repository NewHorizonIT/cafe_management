import { Container, HeroSection, ListProduct } from "@/components/layout";
import Heading from "@/components/ui/Heading";
import AdvancedSearch from "@/components/ui/AdvancedSearch";
import React from "react";

const Home = () => {
  const products = [
    {
      id: 1,
      thumnail:
        "https://th.bing.com/th/id/OIP.CrmDIuoIl4SwrtP7CLSLpgHaEK?rs=1&pid=ImgDetMain",
      name: "Product A",
      price: "20,000VND",
      createdAt: "2025-03-20", // Assume this is a date string
    },
    {
      id: 2,
      thumnail:
        "https://th.bing.com/th/id/OIP.CrmDIuoIl4SwrtP7CLSLpgHaEK?rs=1&pid=ImgDetMain",
      name: "Product A",
      price: "20,000VND",
      createdAt: "2025-03-20", // Assume this is a date string
    },
    {
      id: 3,
      thumnail:
        "https://th.bing.com/th/id/OIP.CrmDIuoIl4SwrtP7CLSLpgHaEK?rs=1&pid=ImgDetMain",
      name: "Product A",
      price: "20,000VND",
      createdAt: "2025-03-20", // Assume this is a date string
    },
    {
      id: 4,
      thumnail:
        "https://th.bing.com/th/id/OIP.CrmDIuoIl4SwrtP7CLSLpgHaEK?rs=1&pid=ImgDetMain",
      name: "Product A",
      price: "20,000VND",
      createdAt: "2025-03-20", // Assume this is a date string
    },
    {
      id: 5,
      thumnail:
        "https://th.bing.com/th/id/OIP.CrmDIuoIl4SwrtP7CLSLpgHaEK?rs=1&pid=ImgDetMain",
      name: "Product A",
      price: "20,000VND",
      createdAt: "2025-03-20", // Assume this is a date string
    },
    {
      id: 6,
      thumnail:
        "https://th.bing.com/th/id/OIP.CrmDIuoIl4SwrtP7CLSLpgHaEK?rs=1&pid=ImgDetMain",
      name: "Product A",
      price: "20,000VND",
      createdAt: "2025-03-20", // Assume this is a date string
    },
  ];

  const handleSearch = (filters) => {
    console.log("Search filters:", filters);
    // Add logic to fetch and display search results based on filters
  };

  return (
    <>
      <HeroSection heroImg="https://dreamheatersystem.com/wp-content/uploads/2019/04/hero-section-image.jpg" />
      <Container className="py-20">
        <Heading
          title="Danh sach do uong pho bien"
          className="text-center"
          des="day la danh sach"
        />
        <AdvancedSearch onSearch={handleSearch} />
        <ListProduct products={products} />
      </Container>
    </>
  );
};

export default Home;
