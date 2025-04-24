import { create } from "zustand";

const useCategoryStore = create((set) => ({
  categories: ["coffee", "tea"],
  setCategories: (categories) => set({ categories }),
}));

export default useCategoryStore;
