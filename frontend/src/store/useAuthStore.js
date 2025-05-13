import { create } from "zustand";
import { persist } from "zustand/middleware";

const useAuthStore = create(
  persist(
    (set) => ({
      isLogin: false,
      data: {
        userName: "Horizon",
        email: "dev@gmail.com",
        role: "warehouser",
      },
      setIsLogin: (val) => set({ isLogin: val }),
      setData: (data) => set({ data }),
    }),
    {
      name: "auth-storage", // key lưu trong localStorage
      partialize: (state) => ({
        isLogin: state.isLogin,
        data: state.data,
      }), // chỉ lưu phần cần thiết (tùy chọn)
    }
  )
);

export default useAuthStore;
