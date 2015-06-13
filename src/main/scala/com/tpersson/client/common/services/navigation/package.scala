package com.tpersson.client.common.services

import de.saxsys.mvvmfx.{ViewModel, FxmlView}

package object navigation {
  type PageViewType = Class[_ <: FxmlView[_ <: ViewModel]]
}
