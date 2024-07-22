<script lang='ts'>
	import { getAllUsers, deleteUser } from '$lib/adminapi';

	let userList = [];

let dataLoaded = false;
let selectedUser = {
	userId: 0,
};

async function loadAll() {
	userList = await getAllUsers();
	dataLoaded = true;
}

	loadAll();

function convertISOToDate(isoDate) {
    const date = new Date(isoDate);
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Months are zero-based
    const year = date.getFullYear();

    return `${day}/${month}/${year}`;
}

	import DeleteUserIcon from '$lib/icon/delete-user.svg?component';
	let delete_modal;
</script>

<!-- Main -->
<main class="max-w-full h-full flex relative overflow-y-hidden">
	<!-- Container -->
	<div id="imageContainer" class="w-full p-4 flex flex-col items-start bg-opacity-0 justify-start gap-4 overflow-y-scroll">
	<!-- Container -->
	{#if dataLoaded}
		<table class="table">
			<!-- head -->
			<thead>
				<tr>
					<th></th>
					<th>Name</th>
					<th>Tag</th>
					<th>Email</th>
					<th>Phone</th>
					<th>Registeration Date</th>
					<th>Role</th>
					<th>Delete?</th>
				</tr>
			</thead>
			<tbody>
				<!-- row 1 -->
				{#each userList as user}
					<tr class="hover">
						<th>{user.userId}</th>
						<td>{user.name}</td>
						<td><a href={"/user/" + user.userId} class="hover:text-primary transition ease-in-out duration-150">{user.nameTag}
						</a></td>
						<td>{user.email}</td>
						<td>{user.phone}</td>
						<td>{convertISOToDate(user.registrationDate)}</td>
						<td>{user.role}</td>
						<td><button on:click={() => { selectedUser = user; delete_modal.showModal(); console.log(user); }}>
							<DeleteUserIcon width="18" height="18" />
						</button></td>
					</tr>
				{/each}
			</tbody>
		</table>
	{:else}
		<p>Fetching...</p>
	{/if}

	</div>
</main>



<dialog bind:this={delete_modal} id="delete_modal" class="modal w-100  text-base-content">
	<!-- Press ESC key or click outside to close -->
	<form method="dialog" class="modal-backdrop">
    <button>close</button>
  </form>

  <div class="modal-box max-w-none max-h-none w-5/12 p-4 pt-12">
    <form method="dialog" class="text-right">
        <button class="btn text-lg btn-sm btn-circle btn-ghost absolute right-2 top-2">✕</button>
    </form>

    <div class="flex gap-2">
    	Do you really want to delete user @{selectedUser.nameTag}
    </div>
                
    <div class="flex justify-end">
    	<button class="btn btn-error btn-sm text-right"
    on:click={() => { deleteUser(selectedUser.userId); delete_modal.close(); }}>
        Confirm </button>
    </div>

</dialog>
		