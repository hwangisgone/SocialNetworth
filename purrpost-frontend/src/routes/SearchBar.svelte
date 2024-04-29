<script lang='ts'>
	import SearchIcon from '$lib/icon/search.svg?component';
</script>

<!-- Search -->
<div class="relative flex-shrink-0 px-2 py-2 w-full text-gray-400 border-b dark:border-indigo-800 dark:focus-within:text-light focus-within:text-gray-700">
	<span class="absolute inset-y-0 inline-flex items-center px-4">
	<SearchIcon width=20 height=20 />
	</span>
	<input
	x-ref="searchInput"
	type="text"
	class="w-full py-2 pl-10 pr-4 border rounded-full dark:bg-dark dark:border-transparent dark:text-light focus:outline-none focus:ring"
	placeholder="Search..."
	/>
</div>