import api from "@/api/api";
import { create } from "zustand";

const useCategoryStore = create((set) => ({
  categories: [], // Dữ liệu categories
  setCategories: (categories) => set({ categories }),
  fetchCategories: async () => {
    try {
      const response = await api.get("/categories");
      set({ categories: response.data });
    } catch (error) {
      console.error("Error fetching categories:", error);
    }
  },
}));

export default useCategoryStore;
