import React from "react";
import AdvancedSearch from "../ui/AdvancedSearch";

const HeroSection = ({ heroImg }) => {
  const handleSearch = (filters) => {
    console.log("Search filters:", filters);
    // Add logic to fetch and display search results based on filters
  };
  return (
    <div
      className="hero min-h-[600px]"
      style={{
        backgroundImage: `url(${heroImg})`,
      }}
    >
      <div className="hero-overlay"></div>
      <div className="hero-content text-neutral-content text-center">
        <div className="max-w-2xl">
          <h1 className="mb-5 text-5xl font-bold">
            Chào mừng bạn đến với MyCoffee
          </h1>
          <p className="mb-5">
            MyCoffee là nơi cung cấp đồ uống chất lượng với nhiều loại thức uống
            đa dạng và phong phú. Chúng tôi cam kết mang đến cho bạn những trải
            nghiệm tuyệt vời nhất với những sản phẩm tốt nhất. Hãy đến và khám
            phá thế giới đồ uống của chúng tôi!
          </p>
          <AdvancedSearch onSearch={handleSearch} />
        </div>
      </div>
    </div>
  );
};

export default HeroSection;
