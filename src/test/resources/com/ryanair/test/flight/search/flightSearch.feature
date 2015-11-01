Feature: Book flights

In order to book flights 
As a potential passenger
I want to be able to search for flights from one certain place to another and to book 
the tickets if available flights exist

Scenario Outline: Should book requested flights if exist
Given A list of passengers
	|title|firstName|lastName|type  |
	|MR   |John     |Smith   |adult |
	|MRS  |Ann      |Smith   |adult |
	|MR   |Robert   |Smith   |adult |
When Searched for flights from <from> to <to> out <out> back <back>
Then I should only see round trip flights for selected directions 
And  Departure date should be selected
And  Arrival date should be selected
And If there are available seats It should be possible to book them

When Booking continued
Then Services page should be opened
And  Personal information for all passengers should be requested

When Information privided
Then Payment page should be displayed

Examples:
	|from|to |out           |back      |
	|DUB |BVA|TOMMOROWS_DATE|IN_A_WEEK |
	|DUB |SXF|15/12/2015	|16/12/2015|