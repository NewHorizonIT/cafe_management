import { useState } from "react";
import { Button, Input } from "../ui";

const FormUser = ({ user, onSave }) => {
  const [formData, setFormData] = useState(
    user || {
      username: "",
      email: "",
      status: "",
      role: "",
    }
  );

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSave(formData);
  };

  return (
    <form onSubmit={handleSubmit} className="form-user">
      <div className="modal-field flex gap-2">
        <label htmlFor="id">ID:</label>
        <span className="gap">{user.id}</span>
      </div>

      <div className="modal-field">
        <label htmlFor="username">Username:</label>
        <Input
          id="username"
          name="username"
          placeholder="Enter username"
          value={formData.username}
          onChange={handleChange}
        />
      </div>

      <div className="modal-field">
        <label htmlFor="email">Email:</label>
        <Input
          id="email"
          name="email"
          placeholder="Enter email"
          value={formData.email}
          onChange={handleChange}
        />
      </div>

      <div className="modal-field">
        <label htmlFor="status">Trạng thái</label>
        <select
          id="status"
          name="status"
          className="select"
          value={formData.status}
          onChange={handleChange}
        >
          <option value="Active">Active</option>
          <option value="Inactive">Inactive</option>
        </select>
      </div>

      <div className="modal-field">
        <label htmlFor="role">Vai trò:</label>
        <select
          id="role"
          name="role"
          className="select"
          value={formData.role}
          onChange={handleChange}
        >
          <option value="">Select a role</option>
          <option value="Customer">Customer</option>
          <option value="Admin">Admin</option>
          <option value="Cashier">Cashier</option>
        </select>
      </div>

      <div className="modal-field">
        <label htmlFor="createdAt">Ngày tạo:</label>
        <span>{user.createdAt}</span>
      </div>

      <div className="modal-field">
        <label htmlFor="updatedAt">Ngày cập nhật:</label>
        <span>{user.updatedAt}</span>
      </div>

      <Button
        type="submit"
        className="btn-save bg-primary text-primary-content"
      >
        {user ? "Cập nhật" : "Thêm mới"}
      </Button>
    </form>
  );
};

export default FormUser;
