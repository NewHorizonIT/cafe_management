import { ToggleTheme } from "@/components/ui";
import useAuthStore from "@/store/useAuthStore";

const Header = () => {
  const dateUser = useAuthStore((state) => state.data);

  return (
    <div className="navbar bg-base-100 shadow px-4">
      <div className="flex-1">
        <h1 className="text-xl font-bold capitalize">
          Dashboard - {dateUser.role}
          <ToggleTheme />
        </h1>
      </div>
    </div>
  );
};

export default Header;
