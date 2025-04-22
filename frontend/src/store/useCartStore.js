import { create } from "zustand";

const useCartStore = create((set) => {
  const savedCart = JSON.parse(localStorage.getItem("cart")) || [];

  return {
    cart: savedCart,
    setCart: (cart) => {
      set({ cart });
      localStorage.setItem("cart", JSON.stringify(cart));
    },
    clearCart: () => {
      set({ cart: [] });
      localStorage.removeItem("cart");
    },
    addToCart: (product) =>
      set((state) => {
        const updatedCart = [...state.cart, { ...product, quantity: 1 }];
        localStorage.setItem("cart", JSON.stringify(updatedCart));
        return { cart: updatedCart };
      }),
    removeFromCart: (productId) =>
      set((state) => {
        const updatedCart = state.cart.filter((item) => item.id !== productId);
        localStorage.setItem("cart", JSON.stringify(updatedCart));
        return { cart: updatedCart };
      }),
    updateQuantity: (productId, quantity) =>
      set((state) => {
        const updatedCart = state.cart.map((item) =>
          item.id === productId && item.quantity !== quantity
            ? { ...item, quantity }
            : item
        );
        localStorage.setItem("cart", JSON.stringify(updatedCart));
        return { cart: updatedCart };
      }),
  };
});

export default useCartStore;
