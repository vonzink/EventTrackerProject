import { Component, OnInit }      from '@angular/core';
import { ActivatedRoute }         from '@angular/router';
@Component({ standalone: true, selector: 'app-underwriting', template: `Underwriting for {{ id }}` })


export class UnderwritingComponent implements OnInit {
  id!: number;
  constructor(private route: ActivatedRoute) {}
  ngOnInit() {
    this.id = +this.route.snapshot.params['id'];
    // call your service to fetch underwriting for this.id…
  }
}
