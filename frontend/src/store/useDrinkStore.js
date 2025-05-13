import { create } from "zustand";
import cappuccino from "../assets/cappuccino.jpg";
import latte from "../assets/latte.jpg";
import espresso from "../assets/espresso.jpg";
import milkCoffee from "../assets/milk-coffe.jpg";
import darkCoffee from "../assets/dark-coffe.jpg";
import teaOrange from "../assets/tea_orange.jpg";

const useDrinkStore = create((set) => ({
  drinks: [],
  setDrinks: (drinks) => set({ drinks }),
}));

export default useDrinkStore;
