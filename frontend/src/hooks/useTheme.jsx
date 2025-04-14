import { useState, useEffect } from "react";

const useTheme = () => {
  const [theme, setTheme] = useState(() => localStorage.getItem("theme"));
  useEffect(() => {
    document.documentElement.setAttribute("data-theme", theme);
    localStorage.setItem("theme", theme);
  }, [theme]);

  const toggleTheme = () => {
    const newTheme = theme === "coffee" ? "dark" : "coffee";
    setTheme(newTheme);
  };
  return { theme, toggleTheme };
};
export default useTheme;
