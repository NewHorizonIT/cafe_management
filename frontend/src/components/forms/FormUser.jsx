import { useState } from "react";
import { Button, Input } from "../ui";

const FormUser = ({ user, onSave }) => {
  const [formData, setFormData] = useState(user || { name: "", role: "" });

  const handleChange = (e) => {
    console.log(e.target);
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSave(formData);
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        name="name"
        placeholder="Name"
        value={formData.name}
        onChange={handleChange}
      />

      <select
        name="role"
        className="select"
        placeholder="Role"
        value={formData.role}
        onChange={handleChange}
      >
        <option value="Customer">Customer</option>
        <option value="Admin">Admin</option>
        <option value="Cashier">Cashier</option>
      </select>
      <Button type="submit">Save</Button>
    </form>
  );
};

export default FormUser;
