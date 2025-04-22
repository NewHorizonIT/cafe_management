import { create } from "zustand";
import cappuccino from "../assets/cappuccino.jpg";
import latte from "../assets/latte.jpg";
import espresso from "../assets/espresso.jpg";
import milkCoffee from "../assets/milk-coffe.jpg";
import darkCoffee from "../assets/dark-coffe.jpg";
import teaOrange from "../assets/tea_orange.jpg";

const useDrinkStore = create((set) => ({
  drinks: [
    {
      id: 1,
      thumbnail: milkCoffee,
      name: "Cà phê sữa đá",
      price: "25,000 VND",
      createdAt: "2025-04-01",
      category: "coffee",
      ingredients: ["Cà phê", "Sữa đặc", "Đá"],
      rating: 4.5,
    },
    {
      id: 2,
      thumbnail: espresso,
      name: "Espresso",
      price: "30,000 VND",
      createdAt: "2025-04-02",
      category: "coffee",
      ingredients: ["Cà phê nguyên chất", "Nước nóng"],
      rating: 4.8,
    },
    {
      id: 3,
      thumbnail: darkCoffee,
      name: "Cà phê đen đá",
      price: "20,000 VND",
      createdAt: "2025-04-03",
      category: "coffee",
      ingredients: ["Cà phê", "Đá", "Nước lọc"],
      rating: 4.2,
    },
    {
      id: 4,
      thumbnail: latte,
      name: "Latte",
      price: "35,000 VND",
      createdAt: "2025-04-04",
      category: "coffee",
      ingredients: ["Espresso", "Sữa tươi", "Bọt sữa"],
      rating: 4.6,
    },
    {
      id: 5,
      thumbnail: cappuccino,
      name: "Cappuccino",
      price: "35,000 VND",
      createdAt: "2025-04-05",
      category: "coffee",
      ingredients: ["Espresso", "Sữa tươi", "Bọt sữa", "Bột cacao"],
      rating: 4.7,
    },
    {
      id: 6,
      thumbnail: teaOrange,
      name: "Trà đào cam sả",
      price: "28,000 VND",
      createdAt: "2025-04-06",
      category: "tea",
      ingredients: ["Trà đen", "Đào ngâm", "Cam tươi", "Sả", "Đá"],
      rating: 4.4,
    },
  ],
  setDrinks: (drinks) => set({ drinks }),
}));

export default useDrinkStore;
