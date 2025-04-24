import { FormUser } from "@/components/forms";
import { Button, Modal, Table } from "@/components/ui";
import React, { useState, useEffect } from "react";

const UserManagement = () => {
  const [users, setUsers] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [currentUser, setCurrentUser] = useState(null);

  useEffect(() => {
    setUsers([
      {
        id: 1,
        username: "john_doe",
        email: "john.doe@example.com",
        status: "Active",
        role: "Admin",
        createdAt: "2025-01-01",
        updatedAt: "2025-04-01",
      },
      {
        id: 2,
        username: "jane_smith",
        email: "jane.smith@example.com",
        status: "Inactive",
        role: "User",
        createdAt: "2025-02-01",
        updatedAt: "2025-04-10",
      },
      {
        id: 3,
        username: "alice_wonder",
        email: "alice.wonder@example.com",
        status: "Active",
        role: "Cashier",
        createdAt: "2025-03-01",
        updatedAt: "2025-04-15",
      },
    ]);
  }, []);

  useEffect(() => {
    console.log("Users state initialized:", users);
  }, [users]);

  useEffect(() => {
    console.log("Modal state:", isModalOpen);
  }, [isModalOpen]);

  const handleAddUser = () => {
    console.log("Add User button clicked");
    setCurrentUser(null);
    setIsModalOpen(true);
  };

  const handleEditUser = (user) => {
    console.log("Editing user:", user);
    setCurrentUser(user);
    setIsModalOpen(true);
  };

  const handleDeleteUser = (userId) => {
    console.log("Deleting user with ID:", userId);
    setUsers(users.filter((user) => user.id !== userId));
  };

  const handleSaveUser = (user) => {
    if (user.id) {
      setUsers(users.map((u) => (u.id === user.id ? user : u)));
    } else {
      setUsers([...users, { ...user, id: Date.now() }]);
    }
    setIsModalOpen(false);
  };

  return (
    <div className="flex flex-col gap-4">
      <h1 className="text-2xl font-bold">Quản lý người dùng</h1>
      <Button
        eventHandler={handleAddUser}
        className="w-[160px] btn bg-primary text-primary-content"
      >
        Thêm người dùng
      </Button>
      <Table
        data={users}
        columns={[
          { accessor: "id", header: "ID" },
          { accessor: "username", header: "User Name" },
          { accessor: "email", header: "Email" },
          { accessor: "status", header: "Trạng thái" },
          { accessor: "role", header: "Vai trò" },
          {
            header: "Hành động",
            render: (user) => (
              <>
                <Button eventHandler={() => handleEditUser(user)}>Edit</Button>
                <Button eventHandler={() => handleDeleteUser(user.id)}>
                  Delete
                </Button>
                <Button
                  eventHandler={() => console.log("Details for user:", user)}
                >
                  Details
                </Button>
              </>
            ),
          },
        ]}
        onRowClick={(user) => handleEditUser(user)}
      />
      {isModalOpen && (
        <Modal
          onClose={() => setIsModalOpen(false)}
          title="User Form"
          isOpen={isModalOpen}
        >
          <FormUser user={currentUser} onSave={handleSaveUser} />
        </Modal>
      )}
    </div>
  );
};

export default UserManagement;
