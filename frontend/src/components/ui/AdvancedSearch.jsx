import React, { useState } from "react";
import { Button, Input } from "./index";

const AdvancedSearch = ({ onSearch }) => {
  const [filters, setFilters] = useState({
    keyword: "",
    category: "",
    priceRange: [0, 100],
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFilters({ ...filters, [name]: value });
  };

  const handleSearch = () => {
    onSearch(filters);
  };

  return (
    <div className="advanced-search space-y-4">
      <Input
        name="keyword"
        placeholder="Keyword"
        value={filters.keyword}
        onChange={handleChange}
        type="search"
        className="input input-bordered w-full focus:outline-none"
      />
      <select
        name="category"
        value={filters.category}
        onChange={handleChange}
        className="select select-bordered w-full"
      >
        <option value="">Select Category</option>
        <option value="Beverages">Beverages</option>
        <option value="Snacks">Snacks</option>
        <option value="Desserts">Desserts</option>
      </select>
      <div className="flex items-center space-x-4">
        <label className="text-sm">Price Range:</label>
        <div className="flex items-center space-x-2">
          <input
            type="number"
            name="minPrice"
            min="0"
            max="100"
            value={filters.priceRange[0]}
            onChange={(e) =>
              setFilters({
                ...filters,
                priceRange: [Number(e.target.value), filters.priceRange[1]],
              })
            }
            className="input input-bordered w-20"
          />
          <span className="text-sm">to</span>
          <input
            type="number"
            name="maxPrice"
            min="0"
            max="100"
            value={filters.priceRange[1]}
            onChange={(e) =>
              setFilters({
                ...filters,
                priceRange: [filters.priceRange[0], Number(e.target.value)],
              })
            }
            className="input input-bordered w-20"
          />
        </div>
      </div>
      <Button onClick={handleSearch} className="btn btn-primary w-full">
        Search
      </Button>
    </div>
  );
};

export default AdvancedSearch;
