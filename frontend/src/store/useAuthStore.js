import { create } from "zustand";

const useAuthStore = create((set) => ({
  isLogin: true,
  data: {
    userName: "Horizon",
    email: "dev@gmail.com",
    role: "admin",
  },
  setIsLogin: (val) => set({ isLogin: val }),
  setData: (data) => set({ data: data }),
  setRole: (role) => set({ role: role }),
}));

export default useAuthStore;
