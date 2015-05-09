CHFiles is a CommandHelper extension that allows you to work with files and directory and interact with them from whithin your server.

Latest release: [v1.0.2](https://github.com/macjuul/CHFiles/releases/tag/v1.0.2)

# Functions:

* **copy_file(FromFile, ToFile)** - Copy a file (or directory) to another location, with a new name

* **create_file(PathToFile)** - Create a new file

* **delete_file(PathToFile)** - Delete an extension file or directory

* **list_files(PathToDirectory)** - Lists all files and directories in given directory

* **rename_file(PathToFile)** - Rename a file

* **write_file(PathToFile, content, [mode])** - Write text to a file. mode is optional, can be OVERWRITE or APPEND

* **file_exists(Path)** - Check if a file exists, returns true or false
