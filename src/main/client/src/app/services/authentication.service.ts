import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { Observable, Subject } from 'rxjs';
import { Apollo } from 'apollo-angular';
import { login } from '../graphql_queries';
import { QueryResponse } from '../interfaces/query-response';
import { map } from 'rxjs/operators';

@Injectable({
	providedIn: 'root',
})
export class AuthenticationService {
	private currentUser: User;
	private _currentUserSubject = new Subject();
	CurrentUserChanged$ = this._currentUserSubject.asObservable();

	constructor(private apollo: Apollo) {}

	getCurrentUser() {
		return this.currentUser;
	}

	setCurrentUser(value: User) {
		this.currentUser = value;
		this._currentUserSubject.next();
	}

	login(email: string, password: string): Observable<QueryResponse> {
		return this.apollo
			.mutate<QueryResponse>({
				mutation: login,
				variables: {
					email: email,
					password: password,
				},
			})
			.pipe(map((result) => result.data));
	}
}
