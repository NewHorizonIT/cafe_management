import { create } from "zustand";

const useAuthStore = create((set) => ({
  isLogin: false,
  setIsLogin: (val) => set({ isLogin: val }),
}));

export default useAuthStore;
