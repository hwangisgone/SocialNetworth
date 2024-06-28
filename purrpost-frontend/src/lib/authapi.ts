import toast from 'svelte-french-toast';
import { goto } from '$app/navigation';

export function logout() {
	localStorage.setItem('authToken', '');
	toast.success("Logged out");
	goto('/login');
}