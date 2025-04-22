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
    <div className="advanced-search my-10">
      <div className="flex justify-between gap-4 mb-4">
        <Input
          name="keyword"
          placeholder="Tên đồ uống"
          value={filters.keyword}
          onChange={handleChange}
          type="search"
          className="input input-bordered w-full focus:outline-none text-base-content"
        />
        <select
          name="category"
          value={filters.category}
          onChange={handleChange}
          className="select select-bordered text-base-content"
        >
          <option value="">--Loại đồ uống--</option>
          <option value="Beverages">Trà</option>
          <option value="Snacks">Coffee</option>
        </select>
        <div className="flex items-center gap-2">
          <label className="text-sm">Giá từ:</label>
          <div className="flex items-center gap-2">
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
              className="input input-bordered w-20 text-base-content"
            />
            <span className="text-sm">đến</span>
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
              className="input input-bordered w-20 text-base-content"
            />
          </div>
        </div>
      </div>
      <Button onClick={handleSearch} className="btn btn-primary w-full">
        Tìm kiếm
      </Button>
    </div>
  );
};

export default AdvancedSearch;
