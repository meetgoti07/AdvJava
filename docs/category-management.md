# Category Management

## Overview
The **Category Management** feature is a critical component of the admin panel, designed to facilitate the creation, modification, and management of product categories within the application. This functionality enhances the organization of products and simplifies the inventory management process for administrators.

## Features
- **View Categories**: Displays a list of all existing categories, including their IDs and names.
- **Add New Category**: Allows administrators to create new categories.
- **Update Existing Category**: Enables modification of the name of an existing category.
- **User Interaction**: Provides feedback through visual cues when a category is selected for updating.

## User Interface
### Layout
1. **Title**: A clear title indicating "Product Category Management" is prominently displayed at the top of the page.
2. **Action Buttons**: Positioned below the title, three buttons are centered on the page:
   - **Save**: To save a newly created category.
   - **Update**: To update the selected category.
   - **Close**: To cancel the current editing session and reset fields.
3. **Category Table**: A centrally aligned table lists all categories with a clean and minimalistic design.

### Button Functionality
- **Save**:
  - This button allows administrators to save a new category. If the input field is empty, a warning will be displayed.
  
- **Update**:
  - This button updates the name of the category currently selected in the table. It requires both a selected category and a non-empty name in the input field.

- **Close**:
  - This button clears the current selection and resets the input field, allowing for a fresh start.

### Category Table
The table features the following columns:
- **ID**: A unique identifier for each category.
- **Name**: The name of the category.

## Workflow
1. **Viewing Categories**:
   - Upon loading the Category Management page, all existing categories are fetched and displayed in the table.
   
2. **Adding a New Category**:
   - To add a new category:
     - Enter the desired category name in the input field.
     - Click the **Save** button. 
     - A success message will be displayed if the category is successfully saved.

3. **Updating an Existing Category**:
   - To update a category:
     - Click on the row of the category you wish to update. The selected row will be highlighted.
     - Modify the category name in the input field.
     - Click the **Update** button.
     - A success message will confirm the update if successful.

4. **Clearing Selections**:
   - If you wish to clear your current selection and input, click the **Close** button. This action will reset the input field and remove the highlight from the selected category.

## API Endpoints
### Fetch Categories
- **Endpoint**: `GET /admin/category/all`
- **Description**: Fetches all categories from the server and populates the table.
- **Response Format**:
  ```json
  [
      {
          "id": 1,
          "name": "Electronics"
      },
      {
          "id": 2,
          "name": "Clothing"
      }
  ]


## Save Category
- **Endpoint**: `POST /admin/category/save`  
- **Description**: Saves a new category to the database.  
- **Payload**:
  ```json
  {
      "name": "New Category Name"
  }
  ```
- **Response Format**: A success message indicating the category has been saved.

## Update Category
- **Endpoint**: `POST /admin/category/update`  
- **Description**: Updates the name of the selected category.  
- **Payload**:
  ```json
  {
      "id": "categoryId",
      "name": "Updated Category Name"
  }
  ```
- **Response Format**: A success message confirming the update.




